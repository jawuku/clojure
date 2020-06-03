(ns functions)

;; Factorial function
(defn factorial [x] (reduce *' (take x (map inc (range)))))
(factorial 100)

;; ClojureBridge Exercises
;; Exercise 1 - Basic Arithmetic
;  How many minutes have elapsed since you arrived at the workshop today?
;  Convert this value from minutes to seconds.
(def minutes 32)
(def sec (* minutes 60))
sec

;; EXERCISE 2 [BONUS]: Minutes and seconds
;  Convert 1000 seconds to minutes and seconds.
(def seconds 1000)
(def whole-minutes (quot seconds 60)) ; quot (quotient) displays integer division
(def leftover-seconds (rem seconds 60)) ; rem displays remainder
(println seconds "seconds equals" whole-minutes "minutes and"
         leftover-seconds "seconds.")
