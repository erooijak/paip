(ns paip.exercise5_1)

;; Exercise 5.1 [s]

(defn pat-match
  "Does pattern match input? Any variable can match anything."
  [pattern input]
  (if (or (string? pattern)
          (string? input)
          (empty? pattern)
          (empty? input))
    (= pattern input)
    (and (pat-match (first pattern) (first input))
         (pat-match (rest pattern) (rest input)))))

;; Would it be a good idea to replace the complex and form in pat-match with the
;; simpler (every #'pat-match pattern input)?

;; Note: I use zipmap and destructure to emulate Lisp's every behaviour
;;       where multiple colls can be passed.

(defn simpler-pat-match
  [pattern input]
  (if (or (string? pattern)
          (string? input)
          (empty? pattern)
          (empty? input))
    (= pattern input)
    (every? #(let [[a b] %] simpler-pat-match a b)
            (into [] (zipmap pattern input)))))

;; => No, misses shorter statements. Example:

;; Default
(pat-match ["a" "b" "c"] ["a" "b" "c"]) ; true
(simpler-pat-match ["a" "b" "c"] ["a" "b" "c"]) ; true

;; Shorter
(pat-match ["a" "b" "c" "d"] ["a" "b" "c"]) ; false
(simpler-pat-match ["a" "b" "c" "d"] ["a" "b" "c"]) ; true
