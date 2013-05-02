classdef Graph
    properties
        from = [];
        to = [];
        edge_labels = {};
        nodes = [];
        node_labels = {};
        nextNode = 1;
    end
    methods (Access = private)
        function [] = checkIndex(g,i)
            if ~any(g.nodes == i)
                error('Bad Index.');
            end
        end
    end
    methods
        function g = Graph()
        end
        
        function ns = children(g,i)
            g.checkIndex(i);
            I = (g.from == i);
            ns = g.to(I);
        end
        
        function n = numNodes(g)
            n = length(g.nodes);
        end
        
        function [i,j,labels] = getEdges(g)
            i = g.from;
            j = g.to;
            labels = g.edge_labels;
        end
        
        function [ns,labels] = getNodes(g)
            ns = g.nodes;
            labels = g.node_labels();
        end
        
        function g = addEdge(g,i,j,label)
            g.checkIndex(i);
            g.checkIndex(j);
            g.from = [ g.from i];
            g.to = [ g.to j];
            g.edge_labels{end+1} = label;
        end
        
        function [g,i] = addNode(g,label)
            i = g.nextNode;
            g.nodes = [ g.nodes i ];
            g.nextNode = g.nextNode + 1;
            g.node_labels{end+1} = label;
        end
    end
       
end