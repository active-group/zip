(defproject active-group/zip "0.1.0-SNAPSHOT"
  :description "Functional hierarchical compositional zippers"
  :url "http://github.com/active-group/reacl/zippers"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2280" :scope "provided"]]

  :plugins [[com.keminglabs/cljx "0.4.0"]
            [lein-cljsbuild "1.0.3"]
            ;; NB: This needs a version of clojurescript.test with the Nashorn runner,
            ;; for example from the nashorn-runner branch from
            ;; https://github.com/active-group/clojurescript.test
            [com.cemerick/clojurescript.test "0.3.2-SNAPSHOT"]
            [org.bodil/lein-nashorn "0.1.2"]]

  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/generated/src"
                   :rules :clj}

                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/src"
                   :rules :cljs}

                  {:source-paths ["test/cljx"]
                   :output-path "target/generated/test/clj"
                   :rules :clj}

                  {:source-paths ["test/cljx"]
                   :output-path "target/generated/test/cljs"
                   :rules :cljs}]}

  :source-paths ["target/generated/src"]

  :test-paths ["target/generated/test/clj"]

  :cljsbuild {:builds
              {:dev {:source-paths ["target/classes"]
                             :compiler {:output-to "target/main.js"
                                        :optimizations :whitespace
                                        :pretty-print true}}
               :test {:source-paths ["target/generated/src"
                                     "target/generated/test/cljs"]
                      :compiler {:output-to "target/test.js"
                                 :optimizations :whitespace
                                 :pretty-print true}}}
              :test-commands {"nashorn" ["jrunscript" "-e" "var global = this" "-f" :nashorn-runner "target/test.js"]}}

  :hooks [cljx.hooks leiningen.cljsbuild])
