Set of Animations
===========================

I put here some usefull animations between activities.
For use it you can copy at your res folder and in the activity class use overridePendingTransition(int id, int id);


You should use it after a finish() or startActivity(Intent)

example:
	startActivity(intent);
	overridePendingTransition(R.anim.push_left_in,  R.anim.not_move_out);

or:
	finish();
	overridePendingTransition(R.anim.push_left_in,  R.anim.not_move_out);

Note:
In a fragent you can use: getActivity().overridePendingTransition(R.anim.push_left_in,  R.anim.not_move_out);
