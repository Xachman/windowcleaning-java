import React from 'react';
import { connect } from 'react-redux';
import { MenuItemLink, getResources, Responsive } from 'react-admin';
import Icon from '@material-ui/icons/List';
import EventIcon from '@material-ui/icons/Event';
import { withRouter } from 'react-router-dom';
//import Responsive from '../layout/Responsive';

const Menu = ({ resources, onMenuClick, logout }) => (
    <div>
        {resources.map(resource => {
            if(resource.hasList) {
                return (
                    <MenuItemLink to={`/`+resource.name} 
                    primaryText={`${resource.name.charAt(0).toUpperCase() + resource.name.slice(1)}`} leftIcon={<Icon />} onClick={onMenuClick} />
                )
            }
        }
        )}
        <MenuItemLink to="/Calendar" primaryText="Calendar" leftIcon={<EventIcon />} onClick={onMenuClick} />
        <Responsive
            small={logout}
            medium={null} // Pass null to render nothing on larger devices
        />
    </div>
);

const mapStateToProps = state => ({
    resources: getResources(state),
});

export default withRouter(connect(mapStateToProps)(Menu));
