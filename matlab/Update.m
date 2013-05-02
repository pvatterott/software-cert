%
%  Every node represents an assignment operation.  In particular:
%
%  xout \in { F1*xin + F2*w + F3*t | 
%             H1*xin + H2*w + H3*t = 0, w in [-1,1]^m, t in {-1,1}^k}.
%
%
classdef Update < handle
    properties
        xout = [];
        Fexpr = [];
        Hexpr = [];
        t = [];
        w = [];
    end
    methods
        function up = Update(xout,Fexpr,Hexpr,t,w)
            if nargin < 1, xout = []; Fexpr = []; end
            if nargin < 3, Hexpr = []; end
            if nargin < 4, t = []; end
            if nargin < 5, w = []; end
            if size(xout,2) > 1
                error('xout must be a column.');
            end
            
            if size(Fexpr,1) ~= length(xout)
                error('xout size does not match.');
            end
            
            up.xout = msspoly(xout);
            up.Fexpr = msspoly(Fexpr);
            up.Hexpr = msspoly(Hexpr);
            up.t = msspoly(t);
            up.w = msspoly(w);
        end
        
        function up = combine(upa,upb)
            if isempty(upb.xout)
                up = upa;
                return;
            elseif isempty(upa.xout)
                up = upb;
                return;
            end
            
            mtch = match(upb.xout,upa.xout);
            
            keep = mtch == 0;
            
            Hexpr = [ upa.Hexpr
                      subs(upb.Hexpr,upa.xout,upa.Fexpr) ];
            Fexpr = [ upa.Fexpr(keep)
                      subs(upb.Fexpr,upa.xout,upa.Fexpr) ];
            
            up = Update([upa.xout(keep);upb.xout],...
                        Fexpr,Hexpr);
        end
    end
end