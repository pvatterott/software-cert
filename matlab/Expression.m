%
%  Every node represents an assignment operation.  In particular:
%
%  xout \in { F1*xin + F2*w + F3*t | 
%             H1*xin + H2*w + H3*t = 0, w in [-1,1]^m, t in {-1,1}^k}.
%
%
classdef Expression < handle
    properties
        expr = [];
    end
    methods
        function br = Expression(expr)
            br.expr = expr;
        end
    end
end