(defproject de.it-agile.currency-converter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot de.it-agile.currency-converter
  :target-path "target/%s"
  :profiles {:test {:dev  {:plugins      [[com.jakemccrary/lein-test-refresh "0.23.0"]
                                          [lein-kibit "0.1.6"]
                                          [lein-nvd "0.5.4"]
                                          [jonase/eastwood "0.3.5"]
                                          [lein-cljfmt "0.5.6"]
                                          [lein-ancient "0.6.15"]
                                          [lein-nsorg "0.2.0"]
                                          [venantius/ultra "0.5.4"]]
                           :jvm-opts     ["-Dme.lomin.sayang.*activate*=true"]
                           }
                    :test {:jvm-opts       ["-Xms2048m" "-Xmx2048m" ~(str "-Djava.io.tmpdir=" (System/getenv "HOME"))]
                           :resource-paths ["test-resources"]}}})
