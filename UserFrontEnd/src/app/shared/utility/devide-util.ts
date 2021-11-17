
const state_map = {
    "switch": {
        "on":"off",
        "off":"on"
    }
}
export function nextState(current_state, device_type) {
    return state_map[device_type][current_state];
}