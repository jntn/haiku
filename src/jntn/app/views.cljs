(ns jntn.app.views
  (:require [jntn.app.state :refer [app-state wpm haiku-author error-state?]]))

(defn get-char-classes [c i]
  (let [pos (:position @app-state)]
    ["char"
     (when (= " " c) "space")
     (when (= pos i) "current")
     (when (= c \return) "br")
     (when (and (= pos i) (error-state?)) "error")]))

(defn letter [c i]
  [:span {:class (get-char-classes c i)} (if (= c " ") "•" c)])

(defn results []
  (when (= :showing-result (:state @app-state))
    [:div {:class ["results" "fade-in"]}
     [:<>
      [:span "Good work! "]
      [:span {:class "wpm"} (wpm)]
      [:span " words per minute."]]
     [:div
      [:span "Take a deep breath and press "]
      [:span {:class "key"} "space"]
      [:span " to continue."]]]))

(defn haiku []
  (let [haiku-char-list (->
                         (:haiku @app-state)
                         (:verse)
                         (seq))]
    [:div {:class "fade-in"}
     [:div {:class "haiku"}
      (map-indexed (fn [i c]
                     ^{:key i} [letter c i])
                   haiku-char-list)]
     [:div {:class "author"} (haiku-author)]
     [results]]))

(defn loading []
  [:div "Loading..."])

(defn debug []
  (print @app-state)
  (print (wpm)))

(defn app []
  (let [loading? (not (:haiku @app-state))]
    [:div {:class "center"}
     [:div
      [(if loading? loading haiku)]]]))
