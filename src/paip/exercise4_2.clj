(ns paip.exercise4_2)

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
                permutation (concat before [elem] after)]
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
;; This question (p.560) states that the algorithm is O(N^2) and asks if we can find a solution that is O(n).

;; First check if my answer is N^2.

;; Let's define a timing macro that does returns the time only in string form:

(defmacro time
  "Evaluates expr and prints the time it took."
  {:added "1.0"}
  [expr]
  `(let [start# (. System (nanoTime))]
     ~expr
     (/ (double (- (. System (nanoTime)) start#)) 1000000.0)))

;; Print the time it takes for N = 1 till N = 20
(map #(hash-map % (time (permutations (range %)))) (range 1 80 8))

;; When we plot this in WolframAlpha we obtain a quadratic plot:
https://www.wolframalpha.com/input/?i=ListPlot%5B%7B1,+0.033902%7D,+%7B9,+0.910123%7D,+%7B17,+10.390859%7D,+%7B25,+41.436351%7D,+%7B33,+109.375972%7D,+%7B41,+246.957869%7D,+%7B49,+423.686675%7D,+%7B57,+698.374029%7D,+%7B65,+1067.940008%7D,+%7B73,+1614.75403%7D%5D

;; I cannot think of a faster algorithm.
