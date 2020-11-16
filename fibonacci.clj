(ns fibonacci)

(defn fibonacci [n]
"Return fibonacci number for `n` iterations
Format: (fibonacci n)" 
  (let [a (atom 0), b (atom 1), added (atom 0)]
    (dotimes [i (dec n)]
      (reset! added ( #(+ %1 %2) @a @b))
      (reset! a @b)
      (reset! b @added))
    @b))

(defn fib-vector
"Gives fibonacci number for `n` iterations as a vector
Format: (fib-vector n)"
[n]
  (let [a (atom 0)
        b (atom 1)
        total (atom 0)
        fib-array (atom [0])]
;; performs additions using mutable variables
;; adds each calculation to the end of a vector
    (loop [i 1]
      (when (< i n)
        (reset! total ( #(+' %1 %2) @a @b))
        (reset! a @b)
        (reset! b @total)
        (swap! fib-array conj @total)
        (recur (inc i))))
    @fib-array))

(fibonacci 10)
; => 55

(fib-vector 10)
; => [0 1 2 3 5 8 13 21 34 55]
