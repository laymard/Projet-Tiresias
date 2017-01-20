class George
  attr_writer :name
end

n = George.new
n.name = "Walter White"
puts n.name # should raise error
