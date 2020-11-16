(ns collatz-conjecture)

(def steps (atom 0))
(def steps-return (atom 0))

(defn collatz [n]
;; only calls function if n != 1
;; increments the step counter with each iteration
;; implements recursive calls
  (if-not (= n 1)
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
