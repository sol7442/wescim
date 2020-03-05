package com.wowsanta.scim.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class ScimAttributeOp {
	
	public enum EqOp{
		EQV{
			public boolean op(ScimResource a, ScimResource b) {
				if(a == null) return true;
				if(b == null) return false;
				
				return a.equals(b);
			}
		},
		NEQ
		{
			public boolean op(ScimResource a, ScimResource b) {
				if(a == null) return true;
				if(b == null) return false;
				
				return a.equals(b);
			}
		};
		
		public abstract boolean op(ScimResource a, ScimResource b);
	}
	public enum LogicOp{
		AND{
			public boolean op(boolean a, boolean b) {
				return a && b;
			}
		},
		OR{
			public boolean op(boolean a, boolean b) {
				return a || b;
			}
		};
		public abstract boolean op(boolean a, boolean b);
	}
	
	@Column(name = "orgOp")
	@Enumerated(EnumType.STRING)
	private EqOp orgOp;
	
	@Column(name = "posOp")
	@Enumerated(EnumType.STRING)
	private EqOp posOp;
	
	@Column(name = "jobOp")
	@Enumerated(EnumType.STRING)
	private EqOp jobOp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org")
	private ScimOrg org;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pos")
	private ScimPos pos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job")
	private ScimJob job;
	
	@Column(name = "org_pos")
	@Enumerated(EnumType.STRING)
	private LogicOp org_pos;
	
	@Column(name = "opr_job")
	@Enumerated(EnumType.STRING)
	private LogicOp opr_job;
	
	
	public ScimAttributeOp(ScimOrg org, ScimPos pos, ScimJob job) {
		this.org = org;
		this.pos = pos;
		this.job = job;
	}
	
	public boolean op(ScimAttribute attr) {

		boolean org_res = res_op(this.org, this.orgOp, attr.getOrg());
		boolean pos_res = res_op(this.pos, this.posOp, attr.getPos());
		boolean job_res = res_op(this.job, this.jobOp, attr.getJob());
		
		boolean opr_res	= logic_op(org_res,org_pos,pos_res);
		boolean result  = logic_op(opr_res,opr_job,job_res);
				
		return result;
	}

	private boolean logic_op(boolean val1, LogicOp op, boolean val2) {
		if(op == null) return LogicOp.AND.op(val1, val2);
		return op.op(val1, val2);
	}

	private boolean res_op(ScimResource res1, EqOp op, ScimResource res2) {
		if(op == null) return EqOp.EQV.op(res1, res2);
		return op.op(res1,res2);
	}
	
	
}
