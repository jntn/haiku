(ns app.views
  (:require [app.state :refer [app-state]]))

(defn get-char-classes [c i]
  (let [pos (:position @app-state)]
    ["char"
     (when (= pos i) "current")
     (when (= c \return) "br")
     (when (and (= pos i) (= :error (:state @app-state))) "error")]))

(defn letter [c i]
  [:span {:class (get-char-classes c i)} c])

(defn results []
  (when (= :showing-result (:state @app-state))
    [:div {:class "results"} [:div "Good work!"] [:div "Take a deep breath and press space to continue"]]))

(defn haiku []
  (let [haiku-char-list (-> (:haikus @app-state)
                            (get (:haiku-index @app-state))
                            (:verse)
                            (seq))]
    [:<> [:div {:class "haiku"} (map-indexed (fn [i c]
                                               ^{:key i} [letter c i])
                                             haiku-char-list)] [results]]))

(defn debug []
  (print @app-state))
