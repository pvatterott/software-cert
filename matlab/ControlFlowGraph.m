classdef ControlFlowGraph < Graph
    properties
        registers = [];
    end
    methods (Access = private, Static)
        function [up,ptr] = followPath(pr,start)
            ptr = start;
            path = {};
            while ~pr.isBranch(ptr) &&  ~pr.isExit(ptr)
                path{end+1} = pr.getNodeLabel(ptr);
                ptr = pr.children(ptr);
            end
            up = Update;
            for i = 1:length(path)
                up = up.combine(path{i});
            end
        end
    end
    methods
        function g = ControlFlowGraph(pr)
        % Takes in a program graph and transforms it into a graph
        % whose nodes represent the entry point of the program, the
        % branch points and the return points.
            g.registers = pr.registers;
            
            % Now, move over the nodes.
            
            [g,entry] = g.addNode('entry');
            branches = zeros(1,length(pr.branches));
            for i = 1:length(pr.branches)
                [g,branches(i)] = g.addNode(pr.getNodeLabel(pr.branches(i)));
            end
            exits = zeros(1,length(pr.exits));
            for i = 1:length(pr.exits)
                [g,exits(i)] = g.addNode(pr.getNodeLabel(pr.exits(i)));
            end
            
            [path,ptr] = ControlFlowGraph.followPath(pr,pr.entry);
            g = g.addEdge(entry,findDst(ptr,pr,branches,exits),path);
            
            for i = 1:length(branches)
                chld=pr.children(pr.branches(i));
                for c = 1:length(chld)
                    [path,ptr] = ControlFlowGraph.followPath(pr,chld(c));
                    g = g.addEdge(branches(i),findDst(ptr,pr,branches,exits),path);
                end
            end

            
            function nd = findDst(ptr,pr,branches,exits)
                I = find(ptr == pr.branches);
                if ~isempty(I), 
                    nd = branches(I);
                else
                    I = find(ptr == pr.exits);
                    if isempty(I),
                        error(['Could not find destination of ' ...
                               'path.']);
                    end
                    nd = exits(I);
                end
            end
            
            keyboard
            
            
            
 
            
            
        end
    end
end