(ns learnclojure)

(print "Hello World")

(+ 1 1)
(- 2 1)
(* 2 4)
(/ 4 2)
(* 3 (- 5 3))

;; simple data types - from Java
(type 1)
(type 1.1)
(type true)
(type "Hi")

;; data types - from Clojure - symbol
; equivalent to (type (quote a))
(type 'a)
; keyword
(type (keyword "a"))
;; list
; equivalent to (type (list 1 2 3))
(type '(1 2 3))
;; vector
; equivalent to (type (vector 1 2 3))
(type [1 2 3])
; vectors are zero indexed - finds 3rd value
(nth (vector 1 2 3 4) 2)
; find first value
(first [1 2 3 4])
; second value
(second [1 2 3 4])
; last value
(last [1 2 3 4])
; first value in a list fastest to find
; last value slowest
; vectors are random access - fast to find via index

;; map: keyword/value pairs, encased by curly brackets
; equivalent to (type (hash-map :a 1 :b 2))
(type {:a 1 :b 2})
; use keyword to lookup value
(:town {:town "Rhyl" :county "Denbighshire"})

;; set: unordered collection of values
; equivalent to (type (hash-set :a "b" 10))
(type #{:a "b" 10})

;; Complex Data Type Summary
;; Literal            equivalent constructor function
;  '(1 2 3)           (list 1 2 3)
;   [1 2 3]           (vector 1 2 3)
;  {:a 1 :b 2}        (hash-map :a 1 :b 2)
;  #{"w" 3 :&}        (hash-set "w" 3 :& 8N)

;; Control Flow

; Constants - hold immutable values
; def defines global constants
; x = "Hello Clojure"
(def x "Hello Clojure")

;let defines constants within the scope of the parentheses
; outputs "Hello, Jason"
(let [x "Jason"]
  (println "Hello," x))

; the original x exists outside the scope of the let function
(str x)

;; Conditional - if
; (if (condition)
; do one action if true
; do another if false)

(if (string? x)
  "x is a string"
  "x is not a string")

;see if y is an even number
(def y 231)

(if (even? y)
  "y is even"
  "y is odd")

;; Falsey values
false
(type nil)

(if nil
  "Truthy"
  "Falsey")

;; doing more than one action in each true or false branch of
;; if statement - use do
; x is not empty, so goes to the falsey branch
;prints "Ok" and also gives :ok return keyword
(if (empty? x)
  nil
  (do
    (println "Ok")
    :ok))

;; if-not reverses the order of functions
;; second falsey case can be optionally omitted
(if-not (empty? x)
  (do
    (println "Ok")
    :ok))

;; when-not wraps all actions in if-not
;; without the need for do
(when-not (empty? x)
  (println "ok")
  :ok)

;; when is the inverse of when-not
(when (not (empty? x)) :ok)

;; case - like a switch statement
; requires a predicate variable
(def z "Hello")

(case z
  "Goodbye" :bye
  "Hello" :hello
  :otherwise)

;; cond - - make your own predicate
(cond
  (= z "Goodbye") :goodbye
   (= (reverse z) "olleH") :olleH
  :otherwise :nothing)

;; bug in reverse - works best on lists or vectors
;; renders strings into separate characters
(reverse z)

; render properly into string
(apply str (reverse z))

;thus
(cond
  (= z "Goodbye") :goodbye
   (= (apply str (reverse z)) "olleH") :olleH
  :otherwise :nothing)
