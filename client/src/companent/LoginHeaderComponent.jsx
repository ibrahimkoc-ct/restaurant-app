import React, {Component} from 'react';

class LoginHeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">

                        <div><a href="/" className="navbar-brand">Urun Siparis Uygulaması</a></div>

                    </nav>
                </header>
            </div>

        );
    }
}

export default LoginHeaderComponent;