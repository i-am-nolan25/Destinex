import {Link} from "react-router-dom";
import Header from "../../../components/header/header-logo-only/Header";
import Footer from "../../../components/footer/Footer";
import errorPic from "../../../assets/img/404_error.png";
import "./error_page.css"

function ErrorPage(props) {
    const { isLoggedIn } = props;

    return (
        <div className={"errorBody"}>
            <Header />
            <div className={"errorMessage"}>
                <div className={"errorPicBody"}>
                    <img className={"errorPic"} src={errorPic} alt={"404_error.png"} />
                </div>
                {isLoggedIn &&
                    <div className={"errorLink"}>
                        <Link to={"/granter-home"} className={"errorToHome"}>Wish Your Way Home</Link>
                    </div>
                }
                {!isLoggedIn &&
                    <div className={"errorLink"}>
                        <Link to={"/signin"} className={"errorToHome"}>Please sign in first!</Link>
                    </div>
                }
            </div>
            <Footer />
        </div>

    );
}

export default ErrorPage;