(ns paip.exercise3_11)

; Exercise 3.11 [m] There is a built-in Common Lisp function that, given a key, a value, and
; an association list, returns a new association list that is extended to include the
; key/value pair. What is the name of this function?

; Clojure's assoc does this:

(assoc {:other-key 'other-value} :key 'value) ; => {:other-key other-value, :key value}
