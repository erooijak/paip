(ns paip.exercise3_12)

; Exercise 3.12 [m] Write a single expression using format that will take a list of words and
; print them as a sentence, with the first word capitalized and a period after the last word.
; You will have to consult a reference to learn new format directives.

; This cannot be done by format. It can for example be done via clojure.string methods:

(defn as-sentence
  "Takes a list of words and creates a sentence with first word capitalized and period at end."
  [word-list]
  (str (clojure.string/capitalize (clojure.string/join " " word-list)) "."))
