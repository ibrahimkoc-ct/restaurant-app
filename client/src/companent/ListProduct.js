import React, {Component} from 'react';
import ListGroup from 'react-bootstrap/ListGroup'

class ListProduct extends Component {
    render() {
        return (
            <div>
                <ListGroup>

                    <ListGroup.Item action variant="success">
                        Success
                    </ListGroup.Item>
                    <ListGroup.Item action variant="danger">
                        Danger
                    </ListGroup.Item>
                    <ListGroup.Item action variant="warning">
                        Warning
                    </ListGroup.Item>
                    <ListGroup.Item action variant="info">
                        Info
                    </ListGroup.Item>
                    <ListGroup.Item action variant="light">
                        Light
                    </ListGroup.Item>
                    <ListGroup.Item action variant="dark">
                        Dark
                    </ListGroup.Item>
                </ListGroup>
            </div>
        );
    }
}

export default ListProduct;