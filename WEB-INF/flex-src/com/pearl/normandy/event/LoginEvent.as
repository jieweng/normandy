package com.pearl.normandy.event
{	
    import com.pearl.normandy.vo.UserVO;
    
    import flash.events.Event;

    public class LoginEvent extends Event
    {
    	public static const	LOGIN:String	= "login";
    	
    	public var loginUser:UserVO
    		
        public function LoginEvent(type:String, user:UserVO) {
                super(type);
    			this.loginUser = user;
        }

        override public function clone():Event {
            return new LoginEvent(type, loginUser);
        }
    }	
}