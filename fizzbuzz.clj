(ns fizzbuzz)

(defn fb-series
"Classic FizzBuzz program - enter maximum number to display:
Displays Fizz for numbers divisible by 3,
Buzz for numbers divisible by 5,
FizzBuzz for numbers both divisible by 3 and 5.
Defaults to 100 if no number specified.
Format: (fb-series n)"
  ([] (fb-series (inc (int (* (rand) 100)))))
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

(defn fizzbuzz
"Displays single number
according to FizzBuzz rules
Displays random number (up to 1000) if unspecified.
Format: (fizzbuzz n)"
  ([] (fizzbuzz (inc (int (* (rand) 1000)))))
  ([n] (cond
         (and (zero? (mod n 3)) (zero? (mod n 5)))
         (println (str n " - FizzBuzz"))

         (zero? (mod n 3))
         (println (str n " - Fizz"))

         (zero? (mod n 5))
         (println (str n " - Buzz"))

         :else
         (println (str n)))))
