(ns bank-transactions)

;; set up variables
(def prices (atom {:pen 1, :book 5, :sack 10}))
(def buyer-acct (ref 100))
(def vend-acct (ref 0))

(def shopping-bag (atom {}))

(defn funds-ok? [item]
  (let [{:keys [pen book sack]} @prices
          pen-funds  (- @buyer-acct pen)
          book-funds (- @buyer-acct book)
          sack-funds (- @buyer-acct sack)]
    (if-not (contains? @prices item)
      (throw (Exception. (str "Sorry, item " item " is not in the inventory. Try again.")))
      (do
        (case item
          :pen   (if-not (neg? pen-funds) true false)
          :book  (if-not (neg? book-funds) true false)
          :sack  (if-not (neg? sack-funds) true false)
          :else false)))))

(defn display-status []
  (println "Funds before transaction are as follows:")
  (println (str "Customer account: €" @buyer-acct))
  (println (str "Seller's account: €" @vend-acct))
  (newline)
  (println (str "Prices of items: " @prices))
  (newline)
  (if (empty? @shopping-bag)
    (println "Your shopping bag is empty.")
    (println (str "Your bag contains " @shopping-bag)))) 

(defn transact [item]
  (let [cost (get @prices item)]
;; facilitate transfer of money between accounts
    (dosync
      (alter buyer-acct - cost)
      (alter vend-acct + cost))
;; fill bag with item. if not already contains item, add key to map
;; otherwise add 1 to quantity of said item
    (if-not (contains? @shopping-bag item)
      (swap! shopping-bag assoc item 1)
      (swap! shopping-bag update item inc))))

(defn buy [item]
;; checks whether buyer has enough money in account
    (if (funds-ok? item)
      (do
        (display-status)
        (println "You are going to buy" item "at a cost of" (@prices item) "euros.")
        (transact item)
        (println "You now have") 
        @shopping-bag)
      "Sorry, you don't have enough funds in your account."))
