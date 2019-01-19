(ns jntn.api.haikus-test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [jntn.api.haikus :as h]
            [clojure.spec.alpha :as s]))

(deftest test-haikus-valid
  (is (s/valid? ::h/haikus h/haikus)))