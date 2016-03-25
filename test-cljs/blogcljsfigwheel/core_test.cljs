(ns blogcljsfigwheel.core-test
  (:require [cljs.test :as t :refer-macros [async deftest is testing]]
            [blogcljsfigwheel.core :refer [encode extract-first-word pig-latin-first-word]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [chan >! <!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(enable-console-print!)

(deftest test-encode-echo
  (testing "Test encode echo with constant as first letter."
    (is (= (encode "hello") "ellohay"))
    (is (= (encode "Hello") "elloHay")))
  (testing "Test encode echo with vowel as first letter."
    (is (= (encode "action") "actionway"))
    (is (= (encode "Action") "Actionway")))
  (testing "Test encode with empty string"
    (is (nil? (encode "")))))

(deftest test-extract-first-word
  (testing "Test extract from first word and return first word and rest of string"
    (is (= ["first" "word in the sentence"] (extract-first-word "first word in the sentence"))))
  (testing "Test extract only one word in sentence."
    (is (= ["first" ""] (extract-first-word "first"))))
  (testing "Test empty string"
    (is (= ["" ""] (extract-first-word "")))))

(deftest test-piglatin-first-word
  (testing "Test changing first word to pig latin"
    (is (= "irstFay word in sentence." (pig-latin-first-word "First word in sentence."))))
  (testing "Test changing only word to pig latin"
    (is (= "irstFay " (pig-latin-first-word "First"))))
  (testing "Test pig latin on empty string"
    (is (= " " (pig-latin-first-word ""))))
  (testing "Test changing nil to pig latin"
    (is (= " " (pig-latin-first-word nil)))))
