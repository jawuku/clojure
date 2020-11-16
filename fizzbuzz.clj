(ns fizzbuzz)

(defn fizzbuzz
  ([] (fizzbuzz 100))
  ([n] 
    (dotimes [i n]  
      (cond
        (and (zero? (mod i 3)) (zero? (mod i 5)))
        (println (str i " - FizzBuzz"))

        (zero? (mod i 3))
        (println (str i " - Fizz"))

        (zero? (mod i 5))
        (println (str i " - Buzz"))

        :else
        (println (str i))))))

