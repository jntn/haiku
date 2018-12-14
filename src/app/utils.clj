(ns app.utils
  (:require [clojure.edn :as edn]
            [clojure.string :as s]))

(defn hook
  {:shadow.build/stage :flush}
  [build-state]
  (if (= :release (:shadow.build/mode build-state))
    (let [output-name (:output-name (first (edn/read-string (slurp "public/js/manifest.edn"))))
          index-html (s/replace (slurp "public/index.html") "main.js" output-name)]
      (spit "public/index.html" index-html)))
  build-state)
