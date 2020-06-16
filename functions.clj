(ns functions)

(defn factorial
 "factorial function multiplies a series of integers from 1 to x
  (factorial x) equal to (* 1 2 3 4 ... x)
  note that (factorial 0) = 1"
  ([] (factorial 0))
  ([x] (reduce *' (map inc (range x)))))

(factorial 50)

(defn bmi
  "Calculates Body Mass Index (BMI)
  BMI = weight(kg) / (height(m) ^ 2)
  Format: (bmi weight height)"
  [weight height]
  (/ weight ( #(* % %) height)))

(bmi 80 1.82)

;; ClojureBridge Exercises
;; Exercise 1 - Basic Arithmetic
;  How many minutes have elapsed since you arrived at the workshop today?
;  Convert this value from minutes to seconds.
(def minutes 32)
(def sec (* minutes 60))
(println sec)

;; EXERCISE 2 [BONUS]: Minutes and seconds
;  Convert 1000 seconds to minutes and seconds.
(def seconds 1000)
(def whole-minutes (quot seconds 60)) ; quot (quotient) displays integer division
(def leftover-seconds (rem seconds 60)) ; rem displays remainder
(println seconds "seconds equals" whole-minutes "minutes and"
         leftover-seconds "seconds.")
