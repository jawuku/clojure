(defn rvrse
"reverse collections, including properly reversing strings"
[c]
  (let [rev-coll (into '() c)]
    (if (string? c)
      (apply str rev-coll)
      rev-coll)))

;;;; exercism - DNA transcription into mRNA
;;;; Convert string of DNA into mRNA bases
;;;; Reject sequences which contain invalid bases

(defn valid-dna-bases?
"If a base found that is not
C, G, A or T in a DNA sequnce, then invalid
(returns falsey value of nil)."
[code]
  (let [valid-set #{\C \G \A \T}]
    (some valid-set code)))

(defn transcription [base]
  (case base
    \G "C"
    \C "G"
    \T "A"
    \A "U"))

(defn dna2rna [dna-code]
;; convert to upper-case if not already
  (let [uprcase (clojure.string/upper-case dna-code)

;; and also convert string to a sequence of characters
        c-array (seq (char-array uprcase))]

;; ensure sequence contains valid bases
    (if (valid-dna-bases? c-array)

;; if so, do transcription into mRNA string, return the result
      (apply str (map transcription c-array))

;; otherwise throw an exception error
      (throw (AssertionError. "Base(s) in supplied DNA string invalid.")))))
