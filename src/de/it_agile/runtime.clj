(ns de.it-agile.runtime
  (:require [clojure.walk :as walk]))

(defn run [mapping plan]
  (walk/postwalk (fn [x]
                   (if-let [f (and (vector? x)
                                   (get mapping (first x)))]
                     (run mapping (apply f (rest x)))
                     x))
                 plan))
