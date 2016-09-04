(ns paip.exercise4_1)

;; First question is Common Lisp specific.

;; Exercise 4.2 [m] Write a function that generates all permutations of its input.

;; Seems like a good idea to use a recursive strategy.
;; The permutations of one element consists only of the element itself.
;; Permutations of two is permutations of one plus the last one added at each possible position.
;; Permutations of n elements is permutations of (n-1) elements plus the n added at every position.

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
  (cond (= (count bag) 1) (first bag)
        :else (conj (mapcat #(add-all-positions %)
                      (first bag)
                      (permutations bag)))))
