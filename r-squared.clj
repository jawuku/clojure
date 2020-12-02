(defn r-squared
"Calculate sum of squares in a collection"
[vctr]
  (let [square (fn [x] (*' x x))]
    (reduce +' (map square vctr))))

(r-squared [-20, -13, -7, -5, 0, 3, 19])
(r-squared [-18, -2, -1, 0, 1, 5, 17])
