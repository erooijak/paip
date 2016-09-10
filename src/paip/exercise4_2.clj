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

;; In the answer of Norvig a question later in the book is referenced.
;; This question (p.560 )states that the algorithm is O(N^2) and asks if we can find a solution that is O(n).

;; First check if my answer is N^2.
;; I think it is since it loops over itself every time.
;; But "meten is weten" (to measure is to know).

;; Let's print the time it takes for N = 1 till N = 10
(map #(println (str "N = " % " " (time (do (permutations (range %)) nil))))
     (range 1 10))

; => "Elapsed time: 0.60416 msecs"
