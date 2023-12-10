import '../header.css';
import {Link} from "react-router-dom";

function Header() {
    return (
        <div className="header">
            <div className="left-sided">
                <div className="logo">
                    <Link className={"link-to"} to={"/"}><span className='logo-destinex'>Destinex</span></Link>
                </div>
            </div>
            <div className="right-sided">
            </div>
        </div>
    );
}

export default Header;
