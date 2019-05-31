(ns de.it-agile.currency-converter-test
  (:require [clojure.test :refer :all]
            [de.it-agile.currency-converter :as cc]
            [de.it-agile.http :as http]
            [de.it-agile.runtime :as runtime]))

(def currencies {:dkk 7.1
                 :usd 1.2
                 :won 1200})

(deftest converts-currencies-test
  (is (= 7.1 (cc/convert-to currencies :dkk 1)))
  (is (= 1.2 (cc/convert-to currencies :usd 1)))
  (is (= 2.4 (cc/convert-to currencies :usd 2)))
  (is (= 1200 (cc/convert-to currencies :won 1))))

(deftest build-convert-currency-plan
  (is (= [::cc/convert [::cc/retrieve :eur] :dkk 1]
         (cc/convert 1 :eur :dkk))))

(deftest parses-string-from-http-test
  (is (= [::http/parse-body [::http/http-get "http://www.xe.com/iso4217.php?q=eur"]]
         (http/retrieve :eur)))

  (let [mapping {::http/http-get   (fn [_] "{\"usd\":1.2}")
                 ::http/parse-body http/parse-body
                 ::cc/retrieve     http/retrieve}]
    (is (= {:usd 1.2}
           (runtime/run mapping
                        [::http/parse-body
                         [::http/http-get "http://www.xe.com/iso4217.php?q=eur"]])))))

(deftest integration-test

  (is (= 2400
         (runtime/run {::cc/convert  cc/convert-to
                       ::cc/retrieve (fn [_] currencies)}
                      [::cc/convert [::cc/retrieve :eur] :won 2])))

  (let [mapping {::cc/convert      cc/convert-to
                 ::http/http-get   (fn [_] "{\"usd\":1.2}")
                 ::http/parse-body http/parse-body
                 ::cc/retrieve     http/retrieve}]
    (is (= 2.4
           (runtime/run mapping
                        (cc/convert 2 :eur :usd))))))

