(ns paip.exercise3_8)

; Exercise 3.8 [m] Some versions of  Kyoto Common Lisp (KCL) have a bug wherein they use the
; rightmost value when more than one keyword/value pair is specified for the same keyword. Change
; the definition of find-all so that it works in KCL.

(defn llast
  "Gets second to last value."
  [coll]
  (-> coll butlast last))

(defn get-value
  "Finds the value for a key from right to left. Returns value."
  [key coll]
  (if (= key (llast coll))
    (last coll)
    (recur key (drop-last 2 coll))))

(defn find-all
  "Find all those elements of sequence that match item, according to the keywords.
  Doesn't alter sequence."
  [item coll & options]
  (cond
    (and (some #(= :test-fn %) options) (some #(= :key-fn %) options))
    (let [test-fn (get-value :test-fn options)
          key-fn (get-value :key-fn options)
          result (remove #(not (test-fn % item)) coll)
          final-result (map key-fn result)]
      final-result)
    (some #(= :test-fn %) options)
    (let [test-fn (get-value :test-fn options)
          final-result (remove #(not (test-fn % item)) options)]
      final-result)
    (some #(= :key-fn %) options)
    (let [key-fn (get-value :key-fn options)
          result (remove #(not (= % item)) coll)
          final-result (map key-fn result)]
      final-result)
    :else (remove #(not (= % item)) coll)))

