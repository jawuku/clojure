;; The Clojure Workshop
;;combine and enter codes on pages 170, 414 and 612 @ checkout

;; Activity 1.01
(println "I am not afraid of parentheses")
(* (+ 1 2 3) (- 10 3))
(println "Well done!")

;; Activity 1.02 - CO2 function
; Estimate = 382 + ((Year - 2006) *2)
(defn co2-estimate
  "Returns a (conservative) year's estimate of carbon dioxide parts per million in the atmosphere"
  [year]
  (let [year-diff (- year 2006)]
    (+ 382 (* 2 year-diff))))

;; my own creation - calculate BMI
; uses 2 functions
(defn bmi-report
"Returns a rating based on Body Mass Index"
[bmi]
  (cond
    (< bmi 19)
    "Underweight"
    (<= 19 bmi 25)
    "Ideal weight"
    (< 25 bmi 30)
    "Overweight"
    (and (>= bmi 30) (< bmi 40))
    "Obese"
    :else
    "Morbidly obese"))

(defn bmi-calc
"Calculates Body Mass Index (BMI): weight / (height ^ 2)"
[weight height]
  (let [ height-squared (* height height)]
    (/ weight height-squared)))

(bmi-report (bmi-calc 60 1.65))

;; calculate realpi
(let [phi (/ (+ 1M (Math/sqrt 5M)) 2)]
  (/ 4 (Math/sqrt phi)))
; => 3.144605511029693   
;; Activity 1.03 Meditate function v2.0

;; calculate factorial

(defn meditate2
"Meditate v2.0 - Reacts to your calmness level (between 1 to 10)"
[s calmness-level]
  (if (< calmness-level 5)
    (clojure.string/upper-case (str s ",i tell ya!"))
    (if (<= 5 calmness-level 9)
      (str (clojure.string/capitalize s))
      (when (= calmness-level 10) (clojure.string/reverse s)))))

(defn meditate2-cond
"Meditate v2.0 - Reacts to your calmness level (between 1 to 10)"
[s calmness-level]
  (cond
    (< calmness-level 5)
    (clojure.string/upper-case (str s ", i tell ya!"))
    (<= 5 calmness-level 9)
    (clojure.string/capitalize s)
    (= calmness-level 10)
    (clojure.string/reverse s)))
    
;; strings
"I am a String"
"I am immutable"

; escape characters such as \" to display " in the string
(println "\"The measure of intelligence is the ability to change.\" - Albert Einstein")
;=> "The measure of intelligence is the ability to change." - Albert Einstein

(def silly-string "I am immutable. I am a silly String")

(clojure.string/replace silly-string "silly" "clever")
;=> "I am immutable. I am a clever String"

silly-string
;=> "I am immutable. I am a silly String" - original doesn't change

(str "That's the way you " "con" "ca" "te" "nate")
;=> "That's the way you concatenate"

; replace substring with something else in a string
; replace all ocurrences
 (clojure.string/replace silly-string "String" "little pillow")
;=> "I am immutable. I am a silly little pillow"

; replace only first occurence of substring in a string
(clojure.string/replace-first silly-string "I am" "Ich bin")
;=> "Ich bin immutable. I am a silly String"

; split string with separator given as a regex e.g.
; for comma, this is #","
; gives result as a vector
(clojure.string/split "hello,this,is,a,string" #",")
;=> ["hello" "this" "is" "a" "string"]

; join multiple strings together from a sequence e.g. vector or list
(def towns ["Stoke" "Keele" "Crewe"])
(clojure.string/join towns)
;=> "StokeKeeleCrewe" 

(clojure.string/join "-" (range 10))
;=> "0-1-2-3-4-5-6-7-8-9"

; join with optional separator e.g. comma
(clojure.string/join ", " towns) 
;=> "Stoke, Keele, Crewe"

; capitalize first letter of string
(clojure.string/capitalize "abcdefg")
;=> "Abcdefg"

; convert all characters to upper case
(clojure.string/upper-case silly-string)
;=> "I AM IMMUTABLE. I AM A SILLY STRING"

; convert all characters to lower case
(clojure.string/lower-case "I AM CALM, REALLY")
; => "i am calm, really"

; check if string includes substring
(clojure.string/includes? silly-string "muta")
;=> true
(clojure.string/includes? silly-string "123")
;=> false

; check if string blank
(clojure.string/blank? "")
;=> true

; check if string ends with particular substring
(clojure.string/ends-with? silly-string "ing")
;=> true

; check whether string starts with specified substring
(clojure.string/starts-with? silly-string "I am")
;=> true

; check first occurence of substring or character in string
; returns nmber of index
; can optionally search forward from an index.
(clojure.string/index-of silly-string "S")
;=> 29

(clojure.string/index-of silly-string "I" 15 )
;=> 16 - the index of the capital I in "I am a silly string"

; find last index of a substring or character
(clojure.string/last-index-of silly-string  "am") ;=> 29
; can also use an optional index (searching backwards)
(clojure.string/last-index-of silly-string  "a" 15) ;=> 10  


; reverse a string
(clojure.string/reverse silly-string)
;=> "gnirtS yllis a ma I .elbatummi ma I"

; trimming whitespace
(def spacey "   I have a lot of spaces either side.    ")

; remove white space at both ends of a string
(clojure.string/trim spacey)
;=> "I have a lot of spaces either side."

; remove white space at the left side of a string
(clojure.string/triml spacey)
;=> "I have a lot of spaces either side.    "

; remove white space at the right side of a string
(clojure.string/trimr spacey)
;=> "   I have a lot of spaces either side."

; remove trailing newline \n and return \r characters from string
(clojure.string/trim-newline "Hello!\n")
;=> "Hello!"

; get substring of main string from start index [optionally to last index exclusive]
(subs "Hello World!" 6)
;=> "World!"

(subs "Hello World!" 1 5)
;=> "ello"

;; exercise 2.01 - obfuscation machine

(defn encode-letter
[s x]
(let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
(str "#" (int code))))

(defn encode
[s]
  (let [word-count (count (clojure.string/split s #" "))]
  (clojure.string/replace s #"\w" (fn [s] (encode-letter s word-count)))))
  
(defn decode-letter
  [x y]
  (let [number (Integer/parseInt (subs x 1))
    letter (char (- (Math/sqrt number) y))]
    (str letter)))

(defn decode [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
   (clojure.string/replace s #"\#\d+" (fn [s] (decode-letter s number-of-words)))))
    
;;Clojure Workshop Notes
;;======================

;; Maps
;; ----

(hash-map :a 1 :b 2) ; key-value pairs
(def map-name (hash-map :a 1 :b 2))
;Keys cannot be duplicated, but values can.

(get map-name :a) ; returns value of key if present, nil if absent

(get map-name :a 0) 
; give default value of 0 (or any other set value) if key absent

(map-name :a) ; same as line 9

(:a map-name) ; as above. key has to be a  colon preceded keyword,
; otherwise it would not work.

;lines 13 and 15 can also add default values for missing keys:-

(map-name :c "not found")

(:d map-name :absent)

(assoc map-name :b 24) ; add new value to given key, or add new key and value.

(update map-name :a * 2) ; allows functions to update key values, e.g.

(update map-name :b - 4) ; decreases value of :key by 4

(update map-name :a inc) ; increases value of key by 1

(dissoc map-name :a) ; remove key(s) from map

(dissoc {:a 1 :b 2} :b) ; => {:a 1}

;; Vectors
;; -------

; Ordered array, accessible from index 0. Duplicates allowed.

[1 2 3] ; square brackets literal

(vector 1 2 3) ; generate map by function

(vec #{1 3 5 7 9}) ; create vector from other collection. => [7 1 3 9 5]

(get [:A :B :C] 0) ; get 1st index (0) - :A

(first [:A :B :C]) ; same as above (:A)

(second [:A :B :C]) ; :B

([:A :B :C] 1) ; same as above (:B)

(get [:A :B :C] 10) ; returns nil if absent

(def fibonacci [0 1 1 2 3 5 8])

(get fibonacci 6) ; 8 (7th value)

(nth fibonacci 6) ; same as above

(fibonacci 6) ; 8 Like maps, vector name can also be used as a function

(conj fibonacci 13 21) ; adds values, always to end of vector

(last [:A :B :C]) ; :C (last value of sequence)


;; Hash-sets
;; ---------

; unordered collection of data. Does not allow duplicates.

#{1 2 3 4} ; literal

(hash-set 1 2 3 4)

(def german-numbers #{"eins" "zwei" "drei"})

(get german-numbers "eins") ; "eins"

(german-numbers "eins") ; same as above. Sets can be used as functions.

(german-numbers "vier") ; returns nil if absent

(get german-numbers "fünf" :abwesend) ; set default value if absent

(contains? german-numbers "drei") ; true

(contains? german-numbers "sechs") ; false

(conj german-numbers "vier" "fünf") ; adds member(s) to set

; can only use keywords as a function to look up value in a set or a map. Otherwise use get or the collection as a look up function.

(disj german-numbers "eins") ; removes member(s) from set.

(set ["hello" "world"]) ; converts another collection to set

;; Lists
;; -----

;ordered sequence of data, like vectors, but elements are added from the start of the sequence.

; Less efficient than vectors when looking up by index. 
; (has to go one by one though the indices, while vectors can
; access each index equally).

; Lists often used in macros, and LIFO data structures,
; when data is accessed at the start of the list.

; Code consists of lists:

(println "The number is" (* (+ 1 (/ 6 2) 3 2)))

; to distinguish between lists containing data, add single quote for literal
'(1 2 3 4)

; get is not used on lists

; nth works, but is slow going to higher indices.

; first and second functions work like vectors

; rest - returns all members but the first

; next does the same.

; difference between next and rest:

; if no other members (sequence has only 1 item):

; next gives a nil

; rest gives an empty list ()

(next '(1)) ; nil

(rest '(1)) ; ()

;; Adding to lists
;; ---------------

; new items are added to the beginning of a list.

; cons - adds item to a sequence

; order different: new item comes in front of collection

(cons "Hello" '("World" "!"))
; -> ("Hello" "World" "!")

; conj - adds item to collection. In lists, adds to beginning, whereas in vectors, to the end.

; conj has the usual "word order"

(conj '(1 2 3) 4) ; => (4 1 2 3)

;; Other functions for collections
;; -------------------------------

; count - counts number of items in collection

(count #{"Java" "JavaScript" ".NET"}) ; -> 3

(count []) ; -> 0

(empty? {}) ; -> true - sees if collection is empty or not (true or false)

; seq - converts map to sequence, as a list of paired vectors (tuples)

(seq {:a 1 :b 2 :c 3}) ; -> ([:a 1] [:b 2] [:c 3])
; order is not guaranteed, unless it is from a sorted map.

; into - puts elements from one collection into another

(into [1 2 3 4] #{5 6 7 8}) ; -> [1 2 3 4 7 6 5 8]

; de-duplicate vector -  can put elements of vector into an empty set

(into #{} [1 2 2 3 3 3 4 4 ]) ; -> #{1 4 3 2}

; convert tuples to map (opposite of seq)

(into {} [ [:a 1] [:b 2] [:c 3] ]) ; -> {:a 1, :b 2, :c 3}

; adds to list at thew front, one item at a time:

(into () [1 2 3 4]) ; -> (4 3 2 1)

;; concat - another way of joining collections.
;; -------------------------------------------

(concat '(1 2) '(3 4)) ; -> (1 2 3 4)

; whereas

(into '(1 2) '(3 4)) ; -> (4 3 1 2)

; concat will return sequences regardless of input type:

(concat #{1 2 3} #{1 2 3 4}) ; -> (1 3 2 1 4 3 2)

(concat {:a 1} ["Hello"]) ; -> ([:a 1] "Hello")

;; sort - sorts a collection. Returns a sequence list
;; --------------------------------------------------

(sort #{:e :a :c :d :f :b}) ; -> (:a :b :c :d :e :f)

; use into to convert a set into a sorted vector:

(into [] (sort #{:e :a :c :d :f :b})) ; -> [:a :b :c :d :e :f]

; conj with maps - add a new key value pair with a tuple:

(conj {:a 1 :b 2} [:c 3]) ; -> {:a 1 :b 2 :c 3}

; use assoc to substitute value in vector by index:
(assoc [:a :b :c :d] 2 :z) ; -> [:a :b :z :d]

;; Nested Data Structures
;; ----------------------

(def gemstone-db {
    :ruby {
      :name "Ruby"
      :stock 120
      :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
      :properties {
        :dispersion 0.018
        :hardness 9.0
        :refractive-index [1.77 1.78]
        :color "Red"
      }
    }
   :diamond {
      :name "Diamond"
      :stock 10
      :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579]
      :properties {
        :dispersion 0.044
        :hardness 10
        :refractive-index [2.417 2.419]
        :color "Typically yellow, brown or gray to colorless"
      }
    }
    :moissanite {
      :name "Moissanite"
      :stock 45
      :sales [7761 3220]
      :properties {
        :dispersion 0.104
        :hardness 9.5
        :refractive-index [2.65 2.69]
        :color "Colorless, green, yellow"
      }
    }
  }
)

; To get nested details, for example the hardness of :moissanite

; have to go through gemstone-db via 
; :moissanite -> :properties -> :hardness

; 1st way - use nested gets
(get (get (get gemstone-db :moissanite) :properties) :hardness)

; 2nd way - use keywords as functions instead of get
(:hardness (:properties (:moissanite gemstone-db)))

; 3rd more elegant way, using get-in. Nested gets are replaced by a single vector
(get-in gemstone-db [:moissanite :properties :hardness])

(= (get (get (get gemstone-db :moissanite) :properties) :hardness)
(:hardness (:properties (:moissanite gemstone-db))) 
(get-in gemstone-db [:moissanite :properties :hardness])) 

; => true - all 3 ways give identical result

; example - get the 2nd refractive index of diamond: 
 (get-in gemstone-db [:diamond :properties :refractive-index 1]) 

;; Pure Functions
;; --------------

; A function where the return value is determined solely by input values.
; It has no side effects, so the bindings it uses are immutable, and
; produces no I/O (e.g. no printing to screen).

; durability function for a gemstone in the database

(defn durability [db gem]
  (get-in db [gem :properties :hardness]))
  
(durability gemstone-db :diamond)  ; -> 10

;; Changing colour properties of gem

; 1) if use assoc, then all elements of specified field are overwritten:

(assoc (:ruby gemstone-db) :properties {:color "Near colourless though pink through all shades of red to a deep crimson"})

{:name "Ruby"
 :stock 120
 :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
 :properties {:color "Near colourless though pink through all shades of red to a deep crimson"}}
 
; other aspects of :properties such as :hardness have disappeared.
 
;; 2) Therefore, can use update combined with into to add the properties

(update (:ruby gemstone-db) :properties into  {:color "Near colourless though pink through all shades of red to a deep crimson"})

{:name "Ruby"
 :stock 120
 :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
 :properties {:dispersion 0.018
              :hardness 9.0
              :refractive-index [1.77 1.78]
              :color "Near colourless though pink through all shades of red to a deep crimson"}}

;; A more elegant way is to use assoc-in, to replace nested values to get the same result as above
(assoc-in gemstone-db [:ruby :properties :color] "Near colourless though pink through all shades of red to a deep crimson")


;; use update-in to change a property in a nested function by a number
(update-in gemstone-db [:ruby :properties :dispersion] - 0.03)

;; change-colour function
(defn change-colour [db gem new-colour]
  (assoc-in db [gem :properties :color] new-colour))

;; record sale of item and update inventory accordingly
;; client-id inserted in the sales vector, and stock level decreased by 1
(defn sell [db gem client-id]
  (let [updated-sales-db (update-in db [gem :sales] conj client-id)
  updated-stock-level (update-in updated-sales-db [gem :stock] dec) ]
  updated-stock-level))
; for each change, have to create a new copy of the exixting database, with each incremental change
; because data structures are immutable

;; Activity 2.01 - Creating persistent changes
;; -------------------------------------------

(def memory-db (atom {}))

(defn read-db [] @memory-db)

(defn write-db [new-db] (reset! memory-db new-db))

;; these functions serve to read and persist a hashmap in memory

;; data structure of database:
 { :table1 {
     :data [], :indexes {} }
   :table2 {
     :data [], :indexes {} } }

(defn create-table
"Creates a new table in our atom database"
[table]
(write-db (assoc (read-db) table {:data [], :indexes {} })))

(defn drop-table
"Deletes a table from the atom database"
[table]
(write-db (dissoc (read-db) table)))

(defn nuke-tables
"Delete all tables, return to blank table"
[]
(reset! memory-db {}))

(defn insert [table record id-key]
  (let [db (read-db)
        new-db (update-in db [table :data] conj record)
        data-count (dec (count (get-in new-db [table :data])))]
        (write-db (update-in new-db [table :indexes id-key] assoc (id-key record) index))))

(defn select-* [table]
  (let [db (read-db)]
    (get-in db [table :data])))
  
(defn select-*-where [table field field-value]
  (let [db (read-db)
        index-value (get-in db [table :indexes field field-value])
        data (get-in db [table :data])]
    (get data index-value)))

(defn insert [table record id-key]
  (let [db (read-db)
        new-db (update-in db [table :data] conj record)
        data-count (dec (count (get-in new-db [table :data])))]
    (if (contains? db table)
      (str "Not overwriting existing table " table ".")
      (write-db (update-in new-db [table :indexes id-key] assoc (id-key record) index)))))

        
        
        
