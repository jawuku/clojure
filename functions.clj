(ns functions)

;; Factorial function
(defn factorial
 "Multiplies series of integers from 1 to x
  (factorial x) equal to (* 1 2 3 4 ... x)
 Blank parameter assumes value of 1"
 ([] (factorial 1)
  ([x] (reduce *' (take x (map inc (range))))))

(factorial 100)

;; Body Mass Index (BMI) Calculation
(defn bmi
  "Body Mass Index (BMI) = weight(kg) / (height(m) ^ 2)
  Format: (bmi weight height)"
  [weight height]
  (/ weight ( #(* % %) height)))

(bmi 60 1.67)

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
