This lisp interpreter handles 3(4) functions:
defvar
defun
print
{function call}

Example usage:
(defvar A 4) | (defvar A (+ 4 2))

(defun func (+ func 3))
    (func 2)

(print (+ 2 2)) | (print (+ 2 A)) | (print (func 2))



