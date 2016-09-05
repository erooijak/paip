(ns paip.exercise4_1)

;; First question is Common Lisp specific.

;; Exercise 4.2 [m] Write a function that generates all permutations of its input.

;; Seems like a good idea to use a recursive strategy.
;; The permutations of zero elements is only the element itself.
;; Permutations of bag is permutations of the rest plus the first element added at
;; every position in its permutations.

(defn add-all-positions
  "Creates collection where element elem is added at every position of coll."
  [elem coll]
  (let [size (count coll)]
    (loop [n 0 acc []]
      (if (> n size) acc
          (let [[before after] (split-at n coll)
                permutation (vec (concat before [elem] after))]
            (recur (inc n) (conj acc permutation)))))))

(defn permutations
  "Generates permutations of input bag."
  [bag]
  (if (empty? bag) [[]]
      (conj (mapcat (partial add-all-positions (first bag))
                    (permutations (rest bag))))))

(count (permutations [1 2 3 4]))
;; => 24
