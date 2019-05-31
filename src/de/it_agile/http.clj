(ns de.it-agile.http
  (:require [clojure.edn :as edn]
            [clojure.string :as string]))

(defn retrieve [currency]
  [::parse-body [::http-get (str "http://www.xe.com/iso4217.php?q="
                                 (name currency))]])

(defn parse-body [body]
  (reduce-kv (fn [m k v]
               (assoc m (keyword (apply str (rest (drop-last k))))
                        (edn/read-string v)))
             {}
             (apply hash-map (string/split (apply str (rest (drop-last body))) #":"))))
