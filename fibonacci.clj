(ns fibonacci)

(defn fibonacci [n]
  (let [a (atom 0), b (atom 1), added (atom 0)]
    (dotimes [i (dec n)]
      (reset! added ( #(+ %1 %2) @a @b))
      (reset! a @b)
      (reset! b @added))
    @b))

(defn fib-vector
"Gives fibonacci number for `n` iterations in a vector"
[n]
  (let [a (atom 0)
        b (atom 1)
        total (atom 0)
        fib_array (atom [0])]
;; performs additions using mutable variables
;; adds each calculation to the end of a vector
    (loop [i 1]
      (when (< i n)
        (reset! total ( #(+' %1 %2) @a @b))
        (reset! a @b)
        (reset! b @total)
        (swap! fib_array conj @total)
        (recur (inc i))))
    @fib_array))

(fibonacci 10)
; => 55

(fib-vector 10)
; => [0 1 2 3 5 8 13 21 34 55]
