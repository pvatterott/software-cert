classdef ProgramGraph < Graph
    properties (Access = public)
        registers = [];
        bounded = [];
        binary = [];
    end
    
    methods (Access = private)

        
        
        function [pr,w] = newBounded(pr,n)
            w = msspoly(ProgramGraph.boundedName,[n length(pr.bounded)]);
            pr.bounded = [ pr.bounded ; w];
        end
        function [pr,t] = newBinary(pr,n)
            t = msspoly(ProgramGraph.binaryName,[n length(pr.binary)]);
            pr.binary = [ pr.binary ; t];
        end
        
        function [pr,br] = createExpr(pr,op)
            if op{1} ~= ProgramGraph.Br && op{1} ~= ProgramGraph.Ret
                error(sprintf('Opcode %d should not have reached this code.',...
                              op{1}));        
            end
            br = Expression(op{2});
        end
        
        function [pr,up] = createUpdate(pr,op)
        %
        %  [pr,up] = createUpdate(pr,op)
        %   
        %  Registers new bounded and binary variables and creates
        %  an update object.
        %
            function [pr,up] = geqZero(pr,tgt,expr)
                [pr,w] = pr.newBounded(1);
                [pr,t] = pr.newBinary(1);
                up = Update(tgt,t,expr-t-w,t,w);
            end
            
            switch op{1},
              case ProgramGraph.Nop,
                up = Update([],[]);
              case ProgramGraph.Assgn,
                up = Update(op{2},op{3});
              case ProgramGraph.BinaryOps,
                tgt = op{2}; a = op{3}; b = op{4};
                switch op{1}
                  case ProgramGraph.Add,
                    up = Update(tgt,a+b);
                  case ProgramGraph.Sub,
                    up = Update(tgt,a-b);
                  case ProgramGraph.Mul,
                    up = Update(tgt,a*b);
                  case {ProgramGraph.Lt,ProgramGraph.Leq},
                    [pr,up] = geqZero(pr,tgt,b-a);
                  case {ProgramGraph.Gt,ProgramGraph.Geq},
                    [pr,up] = geqZero(pr,tgt,a-b);
                  case ProgramGraph.LogOr,
                    [pr,up] = geqZero(pr,tgt,a+b-1);
                  case ProgramGraph.LogAnd,
                    [pr,up] = geqZero(pr,tgt,a+b-2);
                  case ProgramGraph.Div,
                    if ~isdouble(b)
                        error('Only division by scalars allowed.');
                    end
                    up = Update(tgt,a*(1/b));
                end
              otherwise,
                error(sprintf('Opcode %d should not have reached this code.',...
                              op{1}));
            end
        end
        
        function t = isUpdate(pr,idx)
            t = length(pr.children(idx)) == 1;
        end
    end
    
    methods
        function registers = getRegisters(pr)
            registers = pr.registers;
        end
        
        function t = isBranch(pr,idx)
            t = length(pr.children(idx)) == 2;
        end
        
        function t = isExit(pr,idx)
            t = length(pr.children(idx)) == 0;
        end
        
        function brs = branches(pr,i)
            edges = accumarray([ pr.from' ], 1,[pr.numNodes,1]);
            brs = pr.nodes(edges == 2);
            if nargin > 1, brs = brs(i); end
        end
        function exs = exits(pr,i)
            edges = accumarray([ pr.from' ], 1,[pr.numNodes,1]);
            exs = pr.nodes(edges == 0);
            if nargin > 1, exs = exs(i); end
        end
        
        function s = entry(pr)
            s = pr.nodes(1);
        end

        function w = getBounded(pr)
            w = pr.bounded;
        end
        
        
        function j = findNodeIndex(pr,i)
            j = find(pr.nodes == i);
        end
        function l = getNodeLabel(pr,i)
            j = pr.findNodeIndex(i);
            l = pr.node_labels{j};
        end
        function l = getEdgeLabel(pr,i)
            l = pr.edge_labels{i};
        end

        
        function t = getBinary(pr)
            t = pr.binary;
        end
        
        function pr = ProgramGraph(ops,adjacency)
            if isempty(ops)
                error('Empty node list not allowed.');
            end
            if size(ops,1) ~= size(adjacency,1)
                error('node list and adjacency list of unequal length.');
            end
            
            if size(ops,2) ~= 6
                error('Incorrect number of columns in node list.');
            end
            if size(adjacency,2) ~= 2
                error(['Incorrect number of columns in adjacency ' ...
                       'list.']);
            end
            
            
            [ops,registers] = ProgramGraph.findRegisters(ops); 
            pr.registers = registers;
            nodes = zeros(size(ops,1),1);
            for i = 1:length(ops)
                if ops{i}{1} == ProgramGraph.Br || ops{i}{1} == ProgramGraph.Ret
                    [pr,expr] = createExpr(pr,ops{i});
                    [pr,nodes(i)] = pr.addNode(expr);
                else
                    [pr,up] = createUpdate(pr,ops{i});
                    [pr,nodes(i)] = pr.addNode(up);
                end
            end
            
            for i = 1:size(adjacency,1)
                from = nodes(i);
                switch ops{i}{1}
                  case ProgramGraph.Br,
                    con = nodes(adjacency(i,1)+1);
                    alt = nodes(adjacency(i,2)+1);
                    pr = pr.addEdge(from,con,1);
                    pr = pr.addEdge(from,alt,0);
                  case ProgramGraph.Ret,
                  otherwise,
                    to = nodes(adjacency(i,1)+1);
                    pr = pr.addEdge(from,to,sprintf('%d:next',i));
                end
            end
        end
    end
    
    
    
    
    
    properties (Constant, Access = private)
        Nop = -1;
        Br = 0;
        Ret = 1;
        Assgn = 2;
        Add = 3;
        Sub = 4;
        Mul = 5;
        Div = 6;
        LogOr = 11;
        LogAnd = 12;
        Eq = 15;
        Neq = 16;
        Lt = 17;
        Gt = 18;
        Leq = 19;
        Geq = 20;
        CtlFlow  = { ProgramGraph.Br, ProgramGraph.Ret };
        BinaryOps = {ProgramGraph.Add, ProgramGraph.Sub,...
                     ProgramGraph.Mul, ProgramGraph.Div,...
                     ProgramGraph.LogOr, ProgramGraph.LogAnd,...
                     ProgramGraph.Eq,  ProgramGraph.Neq,...
                     ProgramGraph.Lt,  ProgramGraph.Gt,...
                     ProgramGraph.Leq, ProgramGraph.Geq};
        UnaryOps = { ProgramGraph.Assgn };
    end
    
    methods (Static)
        function nm = registerName()
            nm = 'x';
        end
        function nm = boundedName()
            nm = 'w';
        end
        function nm = binaryName()
            nm = 't';
        end
        
        function r = registersInOp(op)
            switch op(1)
              case ProgramGraph.Nop,
              case ProgramGraph.CtlFlow,
                r = op(2);
              case ProgramGraph.UnaryOps,
                r = [ op(2) ];
                if op(3) == 0, r = [ r ; op(4) ]; end
              case ProgramGraph.BinaryOps,
                r = [ op(2) ];
                if op(3) == 0, r = [ r ; op(4) ]; end
                if op(5) == 0, r = [ r ; op(6) ]; end
              otherwise,
                error(sprintf('Unknown opcode: %d',op(1)));
            end
        end
        
        function opout = updateOperand(op,regs,regNum)
            function o = operand(rs,rn,flg,vl)
                if flg == 1, 
                    o = vl;
                else
                    o = rs(find(vl == rn));
                    if isempty(o)
                        error(sprintf('Unknown register: %d\n',vl));
                    end
                end
            end
            
            switch op(1)
              case ProgramGraph.Nop,
                opout = { op(1) };
              case ProgramGraph.CtlFlow,
                opout = { op(1), operand(regs,regNum,op(2),op(3)) };
              case ProgramGraph.UnaryOps,
                opout = { op(1), ...
                          operand(regs,regNum,0,op(2)),...
                          operand(regs,regNum,op(3),op(4))};
              case ProgramGraph.BinaryOps,
                opout = { op(1), ...
                          operand(regs,regNum,0,op(2)),...
                          operand(regs,regNum,op(3),op(4)),...
                          operand(regs,regNum,op(5),op(6))};
              otherwise,
                error(sprintf('Unknown opcode: %d',op(1)));
            end
        end
        
        function [opout,registers] = findRegisters(ops)
            regList = [];
            for i = 1:size(ops,1)
                op = ops(i,:);
                regList = [regList ; ProgramGraph.registersInOp(op)];
            end
            regList = unique(regList);
            
            registers = msspoly(ProgramGraph.registerName,length(regList));
            
            opout = cell(size(ops,1),1);
            for i = 1:size(ops,1)
                opout{i} = ProgramGraph.updateOperand(ops(i,:),registers,regList);
            end
            
        end
    end
end