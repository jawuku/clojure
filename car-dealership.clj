(ns car-dealership
  (:require [clojure.string :as cs]))

;; car database
(def cars-original {:bmw 60000, :ferrari 100000, :fiat 20000})

;; make changeable copy of car database
(def cars-chg (atom cars-original))

;; determine validity of coupon - must be "clj-cars" (lower or uppercase)
(defn valid-coupon?
"Determines validity of coupon according to code"
[code]
  (if (= (cs/upper-case code) "CLJ-CARS")
    true
    false))

;; determine affordability
(defn affordable-only
"Are particular cars affordable at a certain budget?
if not, eliminate from database"
[budget]
  (let [{:keys [bmw ferrari fiat]} cars-original]

    (let [bmw-afford (- budget bmw)
          ferr-afford (- budget ferrari)
          fiat-afford (- budget fiat)]
        
      (when (neg? ferr-afford)
        (swap! cars-chg dissoc :ferrari))

      (when (neg? bmw-afford)
        (swap! cars-chg dissoc :bmw))

      (when (neg? fiat-afford)
        (swap! cars-chg dissoc :fiat)))))

(defn calculate-discount
"Apply 20% discount to eligible cars"
[]
  (let [discount (/ 4 5)]
;; need to include a when clause if :bmw is already deleted
;; this avoids a NullPointerException
    (when (contains? @cars-chg :bmw)
      (swap! cars-chg update :bmw * discount))
    (swap! cars-chg update :fiat * discount)))

(defn car-deals
"Main function - calculates eligible deal
if correct coupon code entered and sufficient budget.
Format: (car-deals budget coupon-code)"  
[budget code]

  (println "\nWelcome to Our Car Deals")
  (println "------------------------")
  (newline)

;; refresh mutable database to original
  (reset! cars-chg cars-original)
;; calculate affordability - remove unaffordable cars from inventory
;; by altering cars-chg hash-map
  (if-not (number? budget)
    (do
      (println "Try again with a number for your budget.")
      (affordable-only 0))
    (affordable-only budget))
  (cond
    (and (valid-coupon? code) (>= budget 50000))
    (do
      (calculate-discount)
      (println "Congratulations - your coupon code is valid!")
      (println "You have a 20% discount on BMWs and Fiats!"))

    (and (valid-coupon? code) (< budget 50000))
    (println "Your code is valid, but because of budget, we can't apply the discount.")

    :else
    (println (str "Sorry, '" code "' isn't a valid coupon code.")))
  (println "Here are your available prices:")
  @cars-chg)

;; with valid coupons
(car-deals 103423 "clj-Cars")

(car-deals 63522 "Clj-CARS")

(car-deals 28519 "Clj-cars")

(car-deals 18934 "Clj-cars")

;; coupons invalid
(car-deals 100000 "clj-carZ")

(car-deals 60000 "python3-cars")

(car-deals 20000 "js-cars")

(car-deals 10000 "commodore-basic-v2-cars")

;; wrong order
(car-deals "clj-cars" 1000000)
