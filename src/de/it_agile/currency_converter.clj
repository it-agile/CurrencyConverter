(ns de.it-agile.currency-converter)

(defn convert-to [currencies currency value]
  (* (currency currencies) value))

(defn convert [value from to]
  [::convert [::retrieve from] to value])