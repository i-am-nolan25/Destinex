import Header from '../../../components/header/header-logo-only/Header';
import SignupForm from './signup-form-wisher/SignupFormWisher';
import Footer from '../../../components/footer/Footer';
import './signup.css';

function Signup() {
    return (
      <div className="signup">
          {/*<header isLogin={false}></header>*/}
          <Header></Header>
          <SignupForm></SignupForm>
          <Footer></Footer>
      </div>
    );
  }

export default Signup;