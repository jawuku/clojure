(ns collatz-conjecture)

(def steps (atom 0))
(def steps-return (atom 0))

(defn number-valid? [n]
  (cond
    (not (number? n))
    (throw (Exception. "Non-numeric input detected. Please enter an integer."))
    
    (not (pos? n))
    (throw (Exception. "Number cannot be 0 or negative. Please try again."))
    
    (not (integer? n))
    (throw (Exception. "You input a floating point number. Please try again with an integer."))
    
    :else
    true))

(defn collatz [n]
;; only calls function if n != 1
;; increments the step counter with each iteration
;; implements recursive calls
  (if (and (not= n 1) (number-valid? n))
    (if (even? n)
      (do
        (swap! steps inc)
        (collatz (/ n 2)))
      (do
        (swap! steps inc)
        (collatz (inc (* 3 n)))))
;; stops accumulation of steps in subsequent function calls
    (do
      (reset! steps-return @steps)
      (reset! steps 0)))
  @steps-return)

(collatz 12)
