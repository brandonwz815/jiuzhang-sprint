class UnionFind:
    
    def __init__(self):
        self.fathers = {}
        self.size_of_sets = {}
        self.number_of_sets = 0
        
    def merge(self, x, y):
        root_x, root_y = self.find(x), self.find(y)
        if root_x != root_y:
            self.fathers[root_x] = root_y
            self.number_of_sets -= 1
            self.size_of_sets[root_y] += self.size_of_sets[root_x]          
    
    def find(self, x):
        root = x
        while self.fathers[root]:
            root = self.fathers[root]
            
        while x != root:
            original_father = self.fathers[x]
            self.fathers[x] = root
            x = original_father
            
        return root
    
    def add(self, x):
        # if self.fathers[x] == None:
        if not x in self.fathers:
            self.fathers[x] = None
            self.size_of_sets[x] = 1
            self.number_of_sets +=1
            
    def is_connected(self, x, y):
        return self.find(x) == self.find(y)
    
    def get_number_of_sets(self):
        return self.number_of_sets()
    
    def get_size_of_sets(self, x):
        return self.size_of_sets[self.find(x)]
    