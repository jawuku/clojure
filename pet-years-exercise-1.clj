
(defn human-years-ratio [pet]
  (let [age-table {:cat 5, :dog 7, :goldfish 10}]
        (get age-table pet -1)))

(defn display-years [pet age]
  (let [equiv (* age (human-years-ratio pet))]
    (if (neg? equiv)
      (str "The pet " pet " is not recognised. Please enter a pet name of :cat :dog or :goldfish, together with a human age.")
      (str "A " pet " of " age " years is equivalent to a human age of " equiv " years."))))
    
(display-years :cat 10)
(display-years :dog 15)
(display-years :goldfish 7)

(display-years :ferret 3)


;; loops
(defn ten2one []
  (loop [x 10]
    (when (pos? x)
      (println x)
      (recur (dec x)))))

(ten2zero)

(defn factorial [n]
  (print (str "Factorial of" n ": "))
  (let [x (atom 1)]
    (while (< @x n)
      (do
        (* @x n)
        (swap! x inc)))
    @x))