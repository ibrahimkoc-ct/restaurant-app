import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        <HeaderComponent/>
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://localhost:3001" className="navbar-brand">Urun Siparis Uygulaması</a> </div>

                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;