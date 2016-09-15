(defproject paip "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.taoensso/timbre "4.7.3"]                 ; Logging
                 [incanter "1.9.1"]]
  :main ^:skip-aot paip.core
  :target-path "target/%s"
                 :profiles {:uberjar {:aot :all}})
