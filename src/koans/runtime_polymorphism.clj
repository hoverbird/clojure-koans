(defn hello
  ([] "Hello World!")
  ([a] (str "Hello, you silly " a "."))
  ([a & more] (str "Hello to this group: "
                   (apply str
                          (interpose ", " (concat (list a) more)))
                   "!")))

(defmulti diet (fn [x] (:eater x)))
(defmethod diet :herbivore [a] "Bambi eats veggies.")
(defmethod diet :carnivore [a] "Simba eats animals.")
(defmethod diet :default [a] "Rich Hickey eats objects.")

(meditations
  "Some functions can be used in different ways - with no arguments"
  (= "Hello World!" (hello))

  "With one argument"
  (= "Hello, you silly world." (hello "world"))

  "Or with many arguments"
  (= "Hello to this group: Peter, Paul, Mary!"
     (hello "Peter" "Paul" "Mary"))

  "Multimethods allow more complex dispatching"
  (= "Bambi eats veggies."
     (diet {:species "deer" :name "Bambi" :age 1 :eater :herbivore}))

  "Different methods are used depending on the dispatch function result"
  (= "Simba eats animals."
     (diet {:species "lion" :name "Simba" :age 1 :eater :carnivore}))

  "You may use a default method when no others match"
  (= "Rich Hickey eats objects."
     (diet {:name "Rich Hickey"})))
